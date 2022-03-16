package de.thro.vv.exercise01_server.models;

import java.util.List;

public class StatusResult
{
    String message;
    List<InvoiceDocument> invoiceDocuments;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<InvoiceDocument> getInvoiceDocuments()
    {
        return invoiceDocuments;
    }

    public void setInvoiceDocuments(List<InvoiceDocument> invoiceDocuments)
    {
        this.invoiceDocuments = invoiceDocuments;
    }
}
