package modele;
import java.io.*;
import java.net.*;

public class Masocket {
	public static Socket socket;
	public static InputStream is ;
	public static OutputStream os;
	public static ObjectOutputStream oos;
	public static ObjectInputStream ois;
	public static String adrserver="localhost";
	public static String adrclient;
	public static Socket getSocket() {
		return socket;
	}
	public static void setSocket(Socket socket) {
		Masocket.socket = socket;
	}
	public static InputStream getIs() {
		return is;
	}
	public static void setIs(InputStream is) {
		Masocket.is = is;
	}
	public static OutputStream getOs() {
		return os;
	}
	public static void setOs(OutputStream os) {
		Masocket.os = os;
	}
	public static ObjectOutputStream getOos() {
		return oos;
	}
	public static void setOos(ObjectOutputStream oos) {
		Masocket.oos = oos;
	}
	public static ObjectInputStream getOis() {
		return ois;
	}
	public static void setOis(ObjectInputStream ois) {
		Masocket.ois = ois;
	}
	public String getAdrserver() {
		return adrserver;
	}
	public void setAdrserver(String adrserver) {
		Masocket.adrserver = adrserver;
	}
	public static String getAdrclient() {
		return adrclient;
	}
	public static void setAdrclient(String adrclient) {
		Masocket.adrclient = adrclient;
	}

}
