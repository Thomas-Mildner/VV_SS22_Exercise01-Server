package de.thro.vv.exercise01_server.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceDocument
{
    String firstName;
    String lastName;
    String invoiceAmount;
    Date invoiceDate;

    public InvoiceDocument(String firstName, String lastName, String invoiceAmount, Date invoiceDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoiceAmount = invoiceAmount;
        this.invoiceDate = invoiceDate;
    }


    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getInvoiceAmount()
    {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount)
    {
        this.invoiceAmount = invoiceAmount;
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString()
    {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return "Invoice{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceDate=" + formatter.format(invoiceDate) +
                '}';
    }
}
