package ar.edu.untdf.monedas.modelos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Martin
 */
public class ServiceOpen implements Service {

    public void getChange(ArrayList<Moneda> moneyList) {
        try {

            String request = "http://openexchangerates.org/api/latest.json?app_id=25fb0f55591b47abb1b86d90926679a4";
            HttpClient client = new DefaultHttpClient();
            HttpGet method = new HttpGet(request);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream rstream = entity.getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(rstream, writer);
            String theString = writer.toString();
            JSONObject json = (JSONObject) JSONSerializer.toJSON(theString);
            JSONObject a = json.getJSONObject("rates");

            for (Moneda m : moneyList) {
                m.setCambio(a.getString(m.getSiglas()));
            }

        } catch (IOException ex) {
            Logger.getLogger(ServiceOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Moneda> getMonedasSoportadas() {
        try {

            String request = "http://openexchangerates.org/currencies.json";
            HttpClient client = new DefaultHttpClient();
            HttpGet method = new HttpGet(request);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream rstream = entity.getContent();
            StringWriter writer = new StringWriter();
            IOUtils.copy(rstream, writer);
            String theString = writer.toString();
            JSONObject json = (JSONObject) JSONSerializer.toJSON(theString);
            ArrayList<Moneda> m = new ArrayList();
            for (Iterator<String> i = json.keys(); i.hasNext();) {
                String key = i.next();
                Moneda me = new Moneda(key, "0", json.getString(key));
                m.add(me);

            }
            return m;
        } catch (IOException ex) {
            Logger.getLogger(ServiceOpen.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
