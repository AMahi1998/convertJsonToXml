package convertJson;

public class Room {
	String roomtypeid;
	String ratetypeid;
	String Fromdate;
	String Todate;
	Boolean tax_inc;
	Object rate;





	public String getRoomtypeid() {
		return roomtypeid;
	}





	public void setRoomtypeid(String roomtypeid) {
		this.roomtypeid = roomtypeid;
	}





	public String getRatetypeid() {
		return ratetypeid;
	}





	public void setRatetypeid(String ratetypeid) {
		this.ratetypeid = ratetypeid;
	}





	public String getFromdate() {
		return Fromdate;
	}





	public void setFromdate(String fromdate) {
		Fromdate = fromdate;
	}





	public String getTodate() {
		return Todate;
	}





	public void setTodate(String todate) {
		Todate = todate;
	}





	public Boolean getTax_inc() {
		return tax_inc;
	}





	public void setTax_inc(Boolean tax_inc) {
		this.tax_inc = tax_inc;
	}





	public Object getRate() {
		return rate;
	}





	public void setRate(Object object) {
		this.rate = object;
	}





	@Override
	public String toString() {
		return "Room [roomtypeid=" + roomtypeid + ", ratetypeid=" + ratetypeid + ", Fromdate=" + Fromdate + ", Todate="
				+ Todate + ", tax_inc=" + tax_inc + ", rate=" + rate + "]";
	}


}
