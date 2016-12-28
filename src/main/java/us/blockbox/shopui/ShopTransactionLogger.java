package us.blockbox.shopui;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//Created 11/23/2016 11:01 PM
@SuppressWarnings("ResultOfMethodCallIgnored")
class ShopTransactionLogger{
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private final File file;
	private final Queue<String> msgQueue = new LinkedList<>();
	private static final Set<ShopTransactionLogger> loggerSet = Collections.synchronizedSet(new HashSet<ShopTransactionLogger>());
	private static final JavaPlugin plugin = ShopUI.plugin;
	private static final int maxLines = 1000;

	ShopTransactionLogger(String fileName){
		this.file = chooseFile(fileName);
		ShopUI.log.info(file.getName());
		loggerSet.add(this);
	}

	void logToFile(String message){
		synchronized(msgQueue){
			msgQueue.add(dateFormat.format(System.currentTimeMillis()) + " " + message);
			if(msgQueue.size() < 50){
				return;
			}
		}
		flushQueue();
	}

	boolean flushQueue(){
		synchronized(msgQueue){
			if(msgQueue.isEmpty()){
				return true;
			}
		}
		ShopUI.log.info("Flushing to file: " + getFile().getName());
		synchronized(file){
			if(!file.exists()){
				try{
					file.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			PrintWriter pw = null;
			try{
				pw = new PrintWriter(new FileWriter(file,true));
			}catch(IOException e){
				e.printStackTrace();
			}
			while(!msgQueue.isEmpty()){
				pw.println(msgQueue.poll());
			}
			pw.flush();
			pw.close();
		}
		synchronized(msgQueue){
			return msgQueue.isEmpty();
		}
	}

	static void flushAllQueues(){
		for(final ShopTransactionLogger logger : loggerSet){
//			ShopUI.log.info("Flushing to file: " + logger.getFile().getName());
			logger.flushQueue();
		}
	}

	public File getFile(){
		return file;
	}

/*	public void setFile(File file){
		this.file = file;
	}*/

	public static int countLines(File file) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	private static File chooseFile(String file){
		int i = -1;
		File testFile = null;
		for(File f : plugin.getDataFolder().listFiles()){
			if(!f.getName().startsWith(file)){
				continue;
			}
			final String[] name = f.getName().split("_");
			final int num = Integer.parseInt(name[name.length - 1].replace(".txt",""));
			if(num > i){
				ShopUI.log.info("found file " + num);
				i = num;
			}
		}
		if(i != -1){
			testFile = new File(plugin.getDataFolder(),file + "_" + i + ".txt");
		}else{
			testFile = new File(plugin.getDataFolder(),file + "_" + 0 + ".txt");
			try{
				testFile.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		int lines = maxLines;
		try{
			lines = countLines(testFile);
		}catch(IOException e){
			e.printStackTrace();
		}
		if(lines > maxLines){
			ShopUI.log.info("Over " + maxLines + " lines, starting new file.");
			testFile = new File(plugin.getDataFolder(),file + "_" + (i+1) + ".txt");
			try{
				testFile.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return testFile;
	}
}
