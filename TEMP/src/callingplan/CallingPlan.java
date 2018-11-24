package callingplan;

public class CallingPlan {

	private String PAY_NAME; // index 1
	private String MESSAGE; // index 2
	private String CALL; // index 3
	private String DATA; // index 4
	private String PRICE; // index 5

	public CallingPlan() {

	}

	public CallingPlan(String PAY_NAME, String MESSAGE, String CALL, String DATA, String PRICE) {
		setPAY_NAME(PAY_NAME);
		setMESSAGE(MESSAGE);
		setCALL(CALL);
		setDATA(DATA);
		setPRICE(PRICE);
	}

	public CallingPlan(CallingPlan original) {
		setPAY_NAME(original.getPAY_NAME());
		setMESSAGE(original.getMESSAGE());
		setCALL(original.getCALL());
		setDATA(original.getDATA());
		setPRICE(original.getPRICE());
	}

	public void printCallingPlanINFO() {
		System.out.printf("요금제 이름:		" + PAY_NAME + "\n");
		System.out.printf("문자용량:		" + MESSAGE + "\n");
		System.out.printf("전화용량:	" + CALL + "\n");
		System.out.printf("데이터용량: " + DATA + "\n");
		System.out.printf("가격:		" + PRICE + "\n");
	}

	public boolean equal(CallingPlan currentCallingPlan) // 상세비교
	{
		String str = null;
		if ((str = currentCallingPlan.getPAY_NAME()) != PAY_NAME)
			return false;
		else if ((str = currentCallingPlan.getMESSAGE()) != MESSAGE)
			return false;
		else if ((str = currentCallingPlan.getCALL()) != CALL)
			return false;
		else if ((str = currentCallingPlan.getDATA()) != DATA)
			return false;
		else if ((str = currentCallingPlan.getPRICE()) != PRICE)
			return false;
		return true;
	}

	public boolean equalName(CallingPlan currentCallingPlan) // 이름비교
	{
		String str = null;
		if ((str = currentCallingPlan.getPAY_NAME()) != PAY_NAME)
			return false;
		return true;
	}

	public String getPAY_NAME() {
		return PAY_NAME;
	}

	public void setPAY_NAME(String PAY_NAME) {
		this.PAY_NAME = PAY_NAME;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String MESSAGE) {
		this.MESSAGE = MESSAGE;
	}

	public String getCALL() {
		return CALL;
	}

	public void setCALL(String CALL) {
		this.CALL = CALL;
	}

	public String getDATA() {
		return DATA;
	}

	public void setDATA(String DATA) {
		this.DATA = DATA;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}

}
