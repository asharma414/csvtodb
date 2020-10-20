package com.abhinav.maven.csvtodb;

public class Record {
	
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private String F;
	private String G;
	private Integer H;
	private Integer I;
	private String J;
	
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public String getE() {
		return E;
	}
	public void setE(String e) {
		E = e;
	}
	public String getF() {
		return F;
	}
	public void setF(String f) {
		F = f;
	}
	public String getG() {
		return G;
	}
	public void setG(String g) {
		G = g;
	}
	public Integer getH() {
		return H;
	}
	public void setH(String h) {
		if (h.equals("TRUE")) {
			H = 1;
		} else if (h.equals("FALSE")) {
			H = 0;
		}
	}
	public Integer getI() {
		return I;
	}
	public void setI(String i) {
		if (i.equals("TRUE")) {
			I = 1;
		} else if (i.equals("FALSE")) {
			I = 0;
		}
	}
	public String getJ() {
		return J;
	}
	public void setJ(String j) {
		J = j;
	}
	
	public boolean isValid() {
		if (!(A.isEmpty() && B.isEmpty() && C.isEmpty() && D.isEmpty() && E.isEmpty() && F.isEmpty() && G.isEmpty() && H == null && I == null && J.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

}
