package com.seraph.hrms.database.entity;import java.text.SimpleDateFormat;import java.util.Date;import javax.persistence.Basic;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.EnumType;import javax.persistence.Enumerated;import javax.persistence.FetchType;import javax.persistence.JoinColumn;import javax.persistence.ManyToOne;import javax.persistence.Table;import javax.persistence.Temporal;import javax.persistence.TemporalType;import javax.persistence.Transient;import javax.validation.constraints.NotNull;import org.hibernate.annotations.NotFound;import org.hibernate.annotations.NotFoundAction;import org.hibernate.annotations.Where;import com.seraph.hrms.database.entity.base.BaseObject;import com.seraph.hrms.enums.DocumentType;import com.seraph.hrms.utility.format.DateFormatter;/** * @author  Adrian Jasper K. Chua * @version 1.0 * @since   11 December 2017 */@Entity(name = "Document")@Table(name = Document.TABLE_NAME)public class Document extends BaseObject {	private static final long serialVersionUID = -1770637043033994720L;		public static final String TABLE_NAME = "document";		private Personnel personnel;		private DocumentType documentType;		private String fileName;		private Date expirationDate;		private String identificationNumber;		private String remarks;	@ManyToOne(targetEntity = Personnel.class, fetch = FetchType.LAZY)	@JoinColumn(name = "personnel_id")	@Where(clause = "valid = 1")	@NotFound(action = NotFoundAction.IGNORE)	public Personnel getPersonnel() {		return personnel;	}	public void setPersonnel(Personnel personnel) {		this.personnel = personnel;	}	@Enumerated(EnumType.STRING)	@NotNull	@Column(name = "document_type", length = 50)	public DocumentType getDocumentType() {		return documentType;	}	public void setDocumentType(DocumentType documentType) {		this.documentType = documentType;	}	@Basic	@Column(name = "file_name")	public String getFileName() {		return fileName;	}		@Transient	public String getHasAttachments() {		if(fileName != null && !fileName.isEmpty()) {			return "Yes";		} else {			return "No";		}	}	public void setFileName(String fileName) {		this.fileName = fileName;	}	@Temporal(value = TemporalType.TIMESTAMP)	@NotNull	@Column(name = "expiration_date")	public Date getExpirationDate() {		return expirationDate;	}		@Transient	public String getFormattedExpirationDate() {		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		return sdf.format(getExpirationDate());	}		@Transient	public String getPrettyExpirationDate() {		return DateFormatter.prettyFormat(getExpirationDate());	}	public void setExpirationDate(Date expirationDate) {		this.expirationDate = expirationDate;	}	@Basic	@Column(name = "identification_number")	public String getIdentificationNumber() {		return identificationNumber;	}	public void setIdentificationNumber(String identificationNumber) {		this.identificationNumber = identificationNumber;	}	@Basic	@Column(name = "remarks")	public String getRemarks() {		return remarks;	}	public void setRemarks(String remarks) {		this.remarks = remarks;	}}
