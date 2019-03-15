package org.kyantra.filters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;


//Jersey Logging Request and Response Entities using Filter
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.message.internal.ReaderWriter;

public class CustomLoggingFilter extends LoggingFeature implements ContainerRequestFilter, ContainerResponseFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext)  throws IOException
    {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
                : requestContext.getSecurityContext().getUserPrincipal());
        sb.append("-Time: ").append(dateFormat.format(date));
        sb.append(" - Path: ").append(requestContext.getUriInfo().getPath());
        sb.append(" - Header: ").append(requestContext.getHeaders());
        sb.append(" - Entity: ").append(getEntityBody(requestContext));
        System.out.println("HTTP REQUEST : " + sb.toString());
    }

    private String getEntityBody(ContainerRequestContext requestContext)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();

        final StringBuilder b = new StringBuilder();
        try
        {
            ReaderWriter.writeTo(in, out);

            byte[] requestEntity = out.toByteArray();
            if (requestEntity.length == 0)
            {
                b.append("").append("\n");
            }
            else
            {
                b.append(new String(requestEntity)).append("\n");
            }
            requestContext.setEntityStream( new ByteArrayInputStream(requestEntity) );

        } catch (IOException ex) {
            //Handle logging error
        }
        return b.toString();
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("-Time ").append(dateFormat.format(date));
        sb.append("Header: ").append(responseContext.getHeaders());
        sb.append(" - Entity: ").append(responseContext.getEntity());
        System.out.println("HTTP RESPONSE : " + sb.toString());
    }
}
