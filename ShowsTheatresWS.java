/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075;

import ee.ttu.idu0075._2015.ws.theatre.GetShowListResponse;
import ee.ttu.idu0075._2015.ws.theatre.GetShowRequest;
import ee.ttu.idu0075._2015.ws.theatre.GetTheatreListResponse;
import ee.ttu.idu0075._2015.ws.theatre.ShowType;
import ee.ttu.idu0075._2015.ws.theatre.TheatreShowListType;
import ee.ttu.idu0075._2015.ws.theatre.TheatreShowType;
import ee.ttu.idu0075._2015.ws.theatre.TheatreType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author sigri
 */
@WebService(serviceName = "TheatreService", portName = "TheatrePort", endpointInterface = "ee.ttu.idu0075._2015.ws.theatre.TheatrePortType", targetNamespace = "http://www.ttu.ee/idu0075/2015/ws/theatre", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/proov.wsdl")
public class ShowsTheatresWS {
    /*
    Kui soovime SOAP teenusega ühist toodete listi,
    siis tuleb teha list staatiliseks, sest SOAP ja 
    REST jaoks tehakse InvoiceService klassist erinev objekt
    */
    static int nextShowId = 1;
    static int nextTheatreId = 1;
    static List<ShowType> ShowList = new ArrayList<ShowType>();
    static List<TheatreType> TheatreList = new ArrayList<TheatreType>();
    public ee.ttu.idu0075._2015.ws.theatre.ShowType getShow(ee.ttu.idu0075._2015.ws.theatre.GetShowRequest parameter) {
        ShowType st = null;
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (int i = 0; i < ShowList.size(); i++) {
                if (ShowList.get(i).getId().equals(parameter.getId())) {
                    st = ShowList.get(i);
                }
            }    
        }
        return st; 
    }

    public ee.ttu.idu0075._2015.ws.theatre.ShowType addShow(ee.ttu.idu0075._2015.ws.theatre.AddShowRequest parameter) {
        ShowType st = new ShowType();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            st.setName(parameter.getName());
            st.setDurationInMins(parameter.getDurationInMins());
            st.setGenreOfShow(parameter.getGenreOfShow());
            st.setId(BigInteger.valueOf(nextShowId++));
            st.setIsAOneManShow(parameter.getIsAOneManShow());
            st.setLanguage(parameter.getLanguage());
            st.setNumOfActors(parameter.getNumOfActors());
            ShowList.add(st);
      }
        return st;
    }

    public ee.ttu.idu0075._2015.ws.theatre.GetShowListResponse getShowList(ee.ttu.idu0075._2015.ws.theatre.GetShowListRequest parameter) {
        GetShowListResponse response = new GetShowListResponse();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (ShowType ShowType : ShowList) {
                response.getShow().add(ShowType);
            }
        }
        return response;
    }

    public ee.ttu.idu0075._2015.ws.theatre.TheatreType getTheatre(ee.ttu.idu0075._2015.ws.theatre.GetTheatreRequest parameter) {
        TheatreType tt = null;
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (int i = 0; i < TheatreList.size(); i++) {
                if (TheatreList.get(i).getId().equals(parameter.getId())) {
                    tt = TheatreList.get(i);
                }
            }    
        }
        return tt;
    }

    public ee.ttu.idu0075._2015.ws.theatre.TheatreType addTheatre(ee.ttu.idu0075._2015.ws.theatre.AddTheatreRequest parameter) {
        TheatreType tt = new TheatreType();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            tt.setId(BigInteger.valueOf(nextTheatreId++));
            tt.setName(parameter.getName());
            tt.setTheatreShowList(new TheatreShowListType());
            TheatreList.add(tt);
        }
        return tt;
    }

    public ee.ttu.idu0075._2015.ws.theatre.GetTheatreListResponse getTheatreList(ee.ttu.idu0075._2015.ws.theatre.GetTheatreListRequest parameter) {
        GetTheatreListResponse response = new GetTheatreListResponse();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (TheatreType tt : TheatreList) {
                response.getTheatre().add(tt);
            }
        }
        return response;
    }

    public ee.ttu.idu0075._2015.ws.theatre.TheatreShowListType getTheatreShowList(ee.ttu.idu0075._2015.ws.theatre.GetTheatreShowListRequest parameter) {
        TheatreShowListType TheatreShowList = null;
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (int i = 0; i < TheatreList.size(); i++) {
                if (TheatreList.get(i).getId().equals(parameter.getTheatreId())) {
                    TheatreShowList = TheatreList.get(i).getTheatreShowList();
                }

            }    
        }
        return TheatreShowList;
    }

    public ee.ttu.idu0075._2015.ws.theatre.TheatreShowType addTheatreShow(ee.ttu.idu0075._2015.ws.theatre.AddTheatreShowRequest parameter) {
    TheatreShowType TheatreShow = new TheatreShowType();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            GetShowRequest ShowRequest = new GetShowRequest();
            ShowRequest.setId(parameter.getShowId());
            ShowRequest.setToken(parameter.getToken());
            TheatreShow.setShow(getShow(ShowRequest));
        
            for (int i = 0; i < TheatreList.size(); i++) {
                if (TheatreList.get(i).getId().equals(parameter.getTheatreId())) {
                    TheatreList.get(i).setName(TheatreList.get(i).getName());
                    TheatreList.get(i).getTheatreShowList().getTheatreShow().add(TheatreShow);
                    return TheatreShow;
                }

            } 
             
                }

        return null;
    }
    
}
