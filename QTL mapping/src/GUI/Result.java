package GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * 
 * This class represents data that are required for calculation
 * Every object characterized by marker, chromosme, position, pvalue, qvalue, signjf
 *
 */
public class Result {
	public StringProperty marker= new SimpleStringProperty();
	public StringProperty chr= new SimpleStringProperty();
	public StringProperty pos= new SimpleStringProperty();
	public StringProperty pvalue= new SimpleStringProperty();
	public StringProperty qvalue= new SimpleStringProperty();
	public StringProperty signjf= new SimpleStringProperty();
	 public final StringProperty markerProperty() {
         return this.marker;
     }
	 public final StringProperty chrProperty() {
         return this.chr;
     }
	 public final StringProperty posProperty() {
         return this.pos;
     }
	 public final StringProperty pvalueProperty() {
         return this.pvalue;
     }
	 public final StringProperty qvalueProperty() {
         return this.qvalue;
     }
	 public final StringProperty signjfProperty() {
         return this.signjf;
     }
	 public final void setmarker(final java.lang.String marker) {
         this.markerProperty().set(marker);
     }
	 public final void setchr(final java.lang.String chr) {
         this.chrProperty().set(chr);
     }
	 public final void setpos(final java.lang.String pos) {
         this.posProperty().set(pos);
     }
	 public final void setpvalue(final java.lang.String pvalue) {
         this.pvalueProperty().set(pvalue);
     }
	 public final void setqvalue(final java.lang.String qvalue) {
         this.qvalueProperty().set(qvalue);
     } 
	 public final void setsignjf(final java.lang.String signjf) {
         this.signjfProperty().set(signjf);
     }
	 public final java.lang.String getmarker() {
         return this.markerProperty().get();
     }
	 public final java.lang.String getchr() {
         return this.chrProperty().get();
     }
	 public final java.lang.String getpos() {
         return this.posProperty().get();
     }
	 public final java.lang.String getpvalue() {
         return this.pvalueProperty().get();
     }
	 public final java.lang.String getqvalue() {
         return this.qvalueProperty().get();
     }
	 public final java.lang.String getsignjf() {
         return this.signjfProperty().get();
     }

	

}
