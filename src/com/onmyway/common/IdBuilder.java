package com.onmyway.common;

import org.apache.commons.id.Hex;
import org.apache.commons.id.uuid.UUID;

/**
 * @Title:
 * @Description:
 * @Create on: Aug 27, 2010 8:32:30 PM
 * @Author:LJY
 * @Version:1.0
 */
public class IdBuilder {

	/**
	 * 用apache工具，生成32位的UUID
	 * @return
	 */
	public static String getId() {
		return new String(Hex.encodeHex(UUID.randomUUID().getRawBytes()));
	}
}
