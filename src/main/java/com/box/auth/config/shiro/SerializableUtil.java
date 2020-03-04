package com.box.auth.config.shiro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.codec.Base64;

public class SerializableUtil {
	public static <T> String serialize(T t) {
		String sessionStr = null;
		if(t!=null) {
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(bos);
				oos.writeObject(t);
				sessionStr = Base64.encodeToString(bos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sessionStr;
	}

	public static <T> T deserialize(String sessionStr) {
		T session = null;
		if(sessionStr!=null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(bis);
				session = (T) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return session;
	}
}
