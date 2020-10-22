package com.abhinav.maven.csvtodb;

public class Record {
	
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private String F;
	private String G;
	public String H;
	public String I;
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
	public int getH() {
		if (H.equals("true")) {
			return 1;
		} else {
			return 0;
		}
	}
	public void setH(String h) {
		H = h;
	}
	public int getI() {
		if (I.equals("true")) {
			return 1;
		} else {
			return 0;
		}
	}
	public void setI(String i) {
		I = i;
	}
	public String getJ() {
		return J;
	}
	public void setJ(String j) {
		J = j;
	}
	
	public boolean isValid() {
		if (A.isEmpty() || B.isEmpty() || C.isEmpty() || D.isEmpty() || E.isEmpty() || F.isEmpty() || G.isEmpty() || H.isEmpty() || I.isEmpty() || J.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
