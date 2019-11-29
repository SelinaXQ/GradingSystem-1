package pojo;

//Table TemplateDetailedCriteria
public class TemplateDetailedCriteria {
	private String dCriID;  // *, Detailed Criteria ID, [HS], created by General Criteria ID(gCriID)[HS] and detailed criteria type(deCriType)
	private String gCriID; // General Criteria ID, [HS], created by courseID(cID) and genenal criteria type(GenCriType)
	private String deCriType; // Detailed Criteria Type, Assignment1, Assignment2
	private double deCriPer; // Detailed Criteria Type 's Percentage
	public TemplateDetailedCriteria(DetailedCriteria dCri) {
		// TODO Auto-generated constructor stub
	}
	public String getdCriID() {
		return dCriID;
	}
	public void setdCriID(String dCriID) {
		this.dCriID = dCriID;
	}
	public String getgCriID() {
		return gCriID;
	}
	public void setgCriID(String gCriID) {
		this.gCriID = gCriID;
	}
	public String getDeCriType() {
		return deCriType;
	}
	public void setDeCriType(String deCriType) {
		this.deCriType = deCriType;
	}
	public double getDeCriPer() {
		return deCriPer;
	}
	public void setDeCriPer(double deCriPer) {
		this.deCriPer = deCriPer;
	}
}
