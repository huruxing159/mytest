package com.goodhope.goldselling.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderState {
	/*
	 * 订单状态的A，B字母开头是为了让map能够有顺序
	 */
	public final static String UNASSIGN = "未分配";
	public final static String ASSIGN_FAILED = "分配失败";
	public final static String ASSIGNED_UNACCEPT = "已分配未接受";
	public final static String ACCEPTED_UNDELIVER = "已接受未发货";
	public final static String DELIVERED_UNCHECK = "已发货未审核";
	public final static String DELIVERE_FAILE = "发货失败";
	public final static String WAIT_REFUNDMENT = "等待退款";
	public final static String REFUNDMENT_SUCCESS = "已退款";
	public final static String CHECKED_UNBALANCE = "已审核未结算";
	public final static String BALANCED = "已结算";
	public final static String REJECT = "拒绝";
	public final static String CHECKE_FAILE = "审核失败";

	public final static Map<String, String> ALLSTATE = new HashMap<String, String>();
	static {
		ALLSTATE.put("UNASSIGN", UNASSIGN);
		ALLSTATE.put("ASSIGN_FAILED", ASSIGN_FAILED);
		ALLSTATE.put("ASSIGNED_UNACCEPT", ASSIGNED_UNACCEPT);
		ALLSTATE.put("ACCEPTED_UNDELIVER", ACCEPTED_UNDELIVER);
		ALLSTATE.put("DELIVERED_UNCHECK", DELIVERED_UNCHECK);
		ALLSTATE.put("DELIVERE_FAILE", DELIVERE_FAILE);
		ALLSTATE.put("WAIT_REFUNDMENT", WAIT_REFUNDMENT);
		ALLSTATE.put("REFUNDMENT_SUCCESS", REFUNDMENT_SUCCESS);
		ALLSTATE.put("CHECKED_UNBALANCE", CHECKED_UNBALANCE);
		ALLSTATE.put("BALANCED", BALANCED);
		ALLSTATE.put("REJECT", REJECT);
		ALLSTATE.put("CHECKE_FAILE", CHECKE_FAILE);
	}

	private final static List<String> STATELIST = new ArrayList<String>();
	static {
		STATELIST.add("UNASSIGN");
		STATELIST.add("ASSIGN_FAILED");
		STATELIST.add("ASSIGNED_UNACCEPT");
		STATELIST.add("REJECT");
		STATELIST.add("ACCEPTED_UNDELIVER");
		STATELIST.add("DELIVERE_FAILE");
		STATELIST.add("WAIT_REFUNDMENT");
		STATELIST.add("REFUNDMENT_SUCCESS");

		STATELIST.add("DELIVERED_UNCHECK");
		STATELIST.add("CHECKE_FAILE");
		STATELIST.add("CHECKED_UNBALANCE");
		STATELIST.add("BALANCED");

	}

	public final static TreeMap<String, String> VENDOR_STATE = new TreeMap<String, String>(new OrderStateCompatetor());

	static {

		VENDOR_STATE.put("ASSIGNED_UNACCEPT", ASSIGNED_UNACCEPT);
		VENDOR_STATE.put("ACCEPTED_UNDELIVER", ACCEPTED_UNDELIVER);
		VENDOR_STATE.put("DELIVERE_FAILE", DELIVERE_FAILE);
		VENDOR_STATE.put("DELIVERED_UNCHECK", DELIVERED_UNCHECK);
		VENDOR_STATE.put("CHECKED_UNBALANCE", CHECKED_UNBALANCE);
		VENDOR_STATE.put("BALANCED", BALANCED);
		VENDOR_STATE.put("REJECT", REJECT);
		VENDOR_STATE.put("CHECKE_FAILE", CHECKE_FAILE);

	}

	public final static Map<String, String> CUSTOMSERVICE_STATE = new TreeMap<String, String>(new OrderStateCompatetor());
	static {

		CUSTOMSERVICE_STATE.put("UNASSIGN", UNASSIGN);
		CUSTOMSERVICE_STATE.put("ASSIGN_FAILED", ASSIGN_FAILED);
		CUSTOMSERVICE_STATE.put("ASSIGNED_UNACCEPT", ASSIGNED_UNACCEPT);
		CUSTOMSERVICE_STATE.put("ACCEPTED_UNDELIVER", ACCEPTED_UNDELIVER);
		CUSTOMSERVICE_STATE.put("DELIVERE_FAILE", DELIVERE_FAILE);
		CUSTOMSERVICE_STATE.put("WAIT_REFUNDMENT", WAIT_REFUNDMENT);
		CUSTOMSERVICE_STATE.put("REFUNDMENT_SUCCESS", REFUNDMENT_SUCCESS);
		CUSTOMSERVICE_STATE.put("DELIVERED_UNCHECK", DELIVERED_UNCHECK);
		CUSTOMSERVICE_STATE.put("CHECKED_UNBALANCE", CHECKED_UNBALANCE);
		CUSTOMSERVICE_STATE.put("BALANCED", BALANCED);
		CUSTOMSERVICE_STATE.put("REJECT", REJECT);
		CUSTOMSERVICE_STATE.put("CHECKE_FAILE", CHECKE_FAILE);

	}

	public final static Map<String, String> FINANCIAL_STATE = new TreeMap<String, String>(new OrderStateCompatetor());
	static {
		FINANCIAL_STATE.put("CHECKED_UNBALANCE", CHECKED_UNBALANCE);
		FINANCIAL_STATE.put("BALANCED", BALANCED);
		FINANCIAL_STATE.put("WAIT_REFUNDMENT", WAIT_REFUNDMENT);
		FINANCIAL_STATE.put("REFUNDMENT_SUCCESS", REFUNDMENT_SUCCESS);
	}

	private static class OrderStateCompatetor implements Comparator<String> {
		@Override
		public int compare(String state1, String state2) {
			int indexOf1 = STATELIST.indexOf(state1);
			int indexOf2 = STATELIST.indexOf(state2);
			return indexOf1 - indexOf2;
		}
	}
}
