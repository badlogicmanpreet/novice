package com.jmon.net;

import com.jcraft.jsch.Channel;

public interface INet {

	public Channel connect(String server, String user, String password, String port);

	public String cmd(String command);

}
