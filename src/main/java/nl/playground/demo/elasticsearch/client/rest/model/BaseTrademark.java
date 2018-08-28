package nl.playground.demo.elasticsearch.client.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "idx_trademarks", type = "trademark")
public class BaseTrademark {

    @Id
    protected String registrationNumber;

    protected Date filingDateTime;
    protected Date expirationDate;
    protected Address holder;
    protected Address representative;
    protected String imageLink;
    protected Date publicationDate;
    protected Date filingDateRenewal;
    protected Date renewalPublication;
    protected String status;

    // getters and setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getFilingDateTime() {
        return filingDateTime;
    }

    public void setFilingDateTime(Date filingDateTime) {
        this.filingDateTime = filingDateTime;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Address getHolder() {
        return holder;
    }

    public void setHolder(Address holder) {
        this.holder = holder;
    }

    public Address getRepresentative() {
        return representative;
    }

    public void setRepresentative(Address representative) {
        this.representative = representative;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getFilingDateRenewal() {
        return filingDateRenewal;
    }

    public void setFilingDateRenewal(Date filingDateRenewal) {
        this.filingDateRenewal = filingDateRenewal;
    }

    public Date getRenewalPublication() {
        return renewalPublication;
    }

    public void setRenewalPublication(Date renewalPublication) {
        this.renewalPublication = renewalPublication;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
