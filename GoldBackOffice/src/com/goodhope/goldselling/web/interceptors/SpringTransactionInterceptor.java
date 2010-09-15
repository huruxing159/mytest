package com.goodhope.goldselling.web.interceptors;

import org.apache.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.goodhope.goldselling.web.TransactionAware;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class SpringTransactionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1068871030664669709L;
	private PlatformTransactionManager transactionManager;
	private static final Logger LOG = Logger.getLogger(SpringTransactionInterceptor.class);
	private transient ThreadLocal<TransactionStatus> tsHolder = new ThreadLocal<TransactionStatus>();

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		if (action instanceof TransactionAware) {
			try {
				return doInTransaction(actionInvocation);
			} catch (Exception e) {
				rollbackTransaction();
				throw e;
			}
		}
		return actionInvocation.invoke();
	}

	private String doInTransaction(ActionInvocation actionInvocation) throws Exception {
		beginTransaction();
		actionInvocation.addPreResultListener(new PreResultListener() {
			@Override
			public void beforeResult(ActionInvocation arg0, String arg1) {
				commitTransaction();
			}
		});
		return actionInvocation.invoke();
	}

	private void rollbackTransaction() {
		final TransactionStatus ts = tsHolder.get();
		if (!ts.isCompleted()) {
			transactionManager.rollback(ts);
			LOG.error("transaction rollbacked");
		}
	}

	private void beginTransaction() {
		LOG.debug("start transaction!");
		final TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
		tsHolder.set(ts);
	}

	void commitTransaction() {
		final TransactionStatus ts = tsHolder.get();
		if (!ts.isCompleted()) {
			transactionManager.commit(ts);
		}
	}

	public final void setTransactionManager(final PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
}
