package com.xxl.job.admin.core.conf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.admin.service.impl.AdminBizImpl;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.log.XxlJobFileAppender;
import com.xxl.job.core.thread.TriggerCallbackThread;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class XxlJobAdminConfig implements InitializingBean{
	@Autowired
	private AdminBiz adminBiz;
    private static XxlJobAdminConfig adminConfig = null;
    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;
        // 调用dubbo服务日志路径init
        XxlJobFileAppender.initLogPath(adminConfig.getExecutorLogPath());
        // 调用dubbo服务callback服务init
        XxlJobExecutor.addAdminBiz(adminBiz);
        // Start Callback-Server
		TriggerCallbackThread.getInstance().start();
    }

    @Value("${xxl.job.mail.host}")
    private String mailHost;

    @Value("${xxl.job.mail.port}")
    private String mailPort;

    @Value("${xxl.job.mail.ssl}")
    private boolean mailSSL;

    @Value("${xxl.job.mail.username}")
    private String mailUsername;

    @Value("${xxl.job.mail.password}")
    private String mailPassword;

    @Value("${xxl.job.mail.sendNick}")
    private String mailSendNick;

    @Value("${xxl.job.login.username}")
    private String loginUsername;

    @Value("${xxl.job.login.password}")
    private String loginPassword;

    @Value("${xxl.job.i18n}")
    private String i18n;
    
    @Value("${xxl.job.dubbo.zk.address}")
    private String dubbozkAddress;
    
    @Value("${xxl.job.executor.logpath}")
    private String executorLogPath;


    public String getMailHost() {
        return mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public boolean isMailSSL() {
        return mailSSL;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getMailSendNick() {
        return mailSendNick;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getI18n() {
        return i18n;
    }

	public String getDubbozkAddress() {
		return dubbozkAddress;
	}

	public void setDubbozkAddress(String dubbozkAddress) {
		this.dubbozkAddress = dubbozkAddress;
	}

	public String getExecutorLogPath() {
		return executorLogPath;
	}

	public void setExecutorLogPath(String executorLogPath) {
		this.executorLogPath = executorLogPath;
	}
	
	

}
