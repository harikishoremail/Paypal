package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;

public class Transaction implements Serializable 
{
	private static final long serialVersionUID = 6448097582414070581L;
	String tDateTime;
	PPAccount account;
	String narration;
	String reference;
	String status;
	float debit;
	float credit;
	String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String gettDateTime() {

		return tDateTime;
	}
	public void settDateTime(String tDateTime) {
		this.tDateTime = tDateTime;
	}
	public PPAccount getAccount() {
		return account;
	}
	public void setAccount(PPAccount account) {
		this.account = account;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
		this.debit = debit;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
}