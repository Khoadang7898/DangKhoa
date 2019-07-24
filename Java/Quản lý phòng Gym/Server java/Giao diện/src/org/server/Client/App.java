package org.server.Client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import com.sun.jersey.api.client.WebResource;


import org.server.Client.listdata;
import java.util.ArrayList;
import java.util.List;

import org.server.Client.QueryServer;
public class App 
{
    public static listdata Query( QueryServer query )
   {
//    	   public static listdata QueryData( QueryServer query )
//    	    {
//    	    	 
    	 
         // Tạo đối tượng Client dựa trên cấu hình.
         Client client = Client.create();
    
         listdata data = new listdata();
         WebResource webResource;
         if(query.getNamequery()=="SELECT")
         {
        	  webResource = client.resource("https://gymcenter.herokuapp.com/data/query/select");
         }
         else
         {
        	 webResource = client.resource("https://gymcenter.herokuapp.com/data/query/upindel");
         }
         ClientResponse response = webResource.accept("application/json").post(ClientResponse.class, query);
    
         // Trạng thái 200 là thành công
         if (response.getStatus() != 200) {
        	 System.out.print("not connected");
         }
         else {
         
          
         listdata list = response.getEntity(listdata.class);

         if(list.getListdichvu()!=null) {
         data.setListdichvu(list.getListdichvu());
         }
         if(list.getListhoivien()!=null) {
         data.setListhoivien(list.getListhoivien());
         }
         if(list.getListnhanvien()!=null)
         {
        	 data.setListnhanvien(list.getListnhanvien());
         }
         if(list.getThietbiphongtap()!=null)
         {
        	 data.setThietbiphongtap(list.getThietbiphongtap());
         }
         data.setResult(list.getResult());

    }
         return data;
}
}
