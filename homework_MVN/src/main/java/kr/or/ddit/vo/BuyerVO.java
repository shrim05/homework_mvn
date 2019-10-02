package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BuyerVO implements Serializable{
	private String buyer_id;
	private String buyer_name;
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	private String buyer_comtel;
	private String buyer_fax;
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	private List<ProdVO> prodList;
	private String buyer_img;
	
	@Override
	public String toString() {
		return "BuyerVO [buyer_id=" + buyer_id + ", buyer_name=" + buyer_name + ", buyer_lgu=" + buyer_lgu
				+ ", buyer_bank=" + buyer_bank + ", buyer_bankno=" + buyer_bankno + ", buyer_bankname=" + buyer_bankname
				+ ", buyer_zip=" + buyer_zip + ", buyer_add1=" + buyer_add1 + ", buyer_add2=" + buyer_add2
				+ ", buyer_comtel=" + buyer_comtel + ", buyer_fax=" + buyer_fax + ", buyer_mail=" + buyer_mail
				+ ", buyer_charger=" + buyer_charger + ", buyer_telext=" + buyer_telext + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyer_id == null) ? 0 : buyer_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyerVO other = (BuyerVO) obj;
		if (buyer_id == null) {
			if (other.buyer_id != null)
				return false;
		} else if (!buyer_id.equals(other.buyer_id))
			return false;
		return true;
	}
	
	
}
