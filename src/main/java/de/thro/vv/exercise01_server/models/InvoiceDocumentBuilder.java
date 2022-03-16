package de.thro.vv.exercise01_server.models;

import java.util.Date;

public class InvoiceDocumentBuilder
{
    private String firstName;
    private String lastName;
    private String invoiceAmount;
    private Date invoiceDate;

    public InvoiceDocumentBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public InvoiceDocumentBuilder setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public InvoiceDocumentBuilder setInvoiceAmount(String invoiceAmount)
    {
        this.invoiceAmount = invoiceAmount;
        return this;
    }

    public InvoiceDocumentBuilder setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public InvoiceDocument createInvoiceDocument()
    {
        return new InvoiceDocument(firstName, lastName, invoiceAmount, invoiceDate);
    }
}