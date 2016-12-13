package abhibane;

import static com.voxeo.tropo.Key.ASYNC_UPLOAD;
import static com.voxeo.tropo.Key.ATTEMPTS;
import static com.voxeo.tropo.Key.BARGEIN;
import static com.voxeo.tropo.Key.EVENT;
import static com.voxeo.tropo.Key.FORMAT;
import static com.voxeo.tropo.Key.INTERDIGIT_TIMEOUT;
import static com.voxeo.tropo.Key.MAX_SILENCE;
import static com.voxeo.tropo.Key.MAX_TIME;
import static com.voxeo.tropo.Key.NAME;
import static com.voxeo.tropo.Key.PASSWORD;
import static com.voxeo.tropo.Key.TERMINATOR;
import static com.voxeo.tropo.Key.TIMEOUT;
import static com.voxeo.tropo.Key.TO;
import static com.voxeo.tropo.Key.URL;
import static com.voxeo.tropo.Key.USERNAME;
import static com.voxeo.tropo.Key.VALUE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.TropoSession;
import com.voxeo.tropo.actions.Do;
import com.voxeo.tropo.actions.RecordAction;
import com.voxeo.tropo.actions.TransferAction;
import com.voxeo.tropo.enums.Format;

@RestController
@RequestMapping("/abhibane/api/v1")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class Controller {
    
 // object to fetch the details of the http request
    @Autowired
    private HttpServletRequest httpServletRequest;
    
    static String recordPath;
    
    @RequestMapping(value = "/addHeader", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String initiateCallSessions(@RequestBody String sessionJson, @RequestParam("headername") String headerName,
            @RequestParam("customerguid") String customerGuid, @RequestParam("headernumber") String headerNumber,
            @RequestParam("transfernumber") String transferNumber) {
        
        Tropo tropo = new Tropo();
        
        TropoSession session = tropo.session(sessionJson);
        
        System.out.println(session);
        String headerContent = "<sip:" + headerNumber + "@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        //String diversion2 = "<sip:12345678@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        //String diversion3 = "<sip:23456789@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        //String diversion4 = "<sip:3456789@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        
        
        tropo.on(EVENT("continue")).say("transfer completed, thank you");
        tropo.on(EVENT("hangup")).say("transfer hangup, hanging the call");
        tropo.on(EVENT("incomplete")).say("transfer incomplete, hanging the call");
        tropo.on(EVENT("error")).say("transfer errored, thank you");
        tropo.say("transferring call towards Avril...");
        TransferAction transferAction = tropo.transfer(TO(transferNumber + "@sip.tropo.com"), NAME("abhibane-transfer"), TIMEOUT(10.0f));
        transferAction.headers(new String[] { headerName, headerContent });
        //transferAction.headers(new String[] { headerName, diversion2 });
        //transferAction.headers(new String[] { headerName, diversion3 });
        //transferAction.headers(new String[] { headerName, diversion4 });
        
        System.out.println(tropo.text());
        
        return tropo.text();
        
    }
   
    
    @RequestMapping(value = "/executeRNA", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String initiateCallSessions(@RequestBody String sessionJson){
        
        Tropo tropo = new Tropo();
        TropoSession session = tropo.session(sessionJson);        
        System.out.println(session);
        
        String str = formRecordSampleBody();
        System.out.println(str.toString());
        return str;
    }
    
    @RequestMapping(value = "/upload", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String upload(@RequestBody String path){
       
        recordPath = path;
        System.out.println(recordPath);
        return recordPath;
    }
  
    private String formRecordSampleBody() {
        
        System.out.println("*******Printing Session body******");
        Tropo tropo = new Tropo();
        System.out.println("*******Session body End******");
        
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getRequestURL());
        tropo.on("continue", "/continue");
         
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH_mm_ss");
        //Date date = new Date();
       // String fileName = "abhibane_"+dateFormat.format(date);
        RecordAction recordAction = tropo.record(
                NAME("abhibane"), URL("ftp://tropoweb.comxa.com/record/abhibaneRecording.mp3"),
                BARGEIN(false), MAX_SILENCE(3.0f), MAX_TIME(300.0f), ATTEMPTS(1), TIMEOUT(5.0f),
                INTERDIGIT_TIMEOUT(1), ASYNC_UPLOAD(false),FORMAT(Format.MP3), USERNAME("a6661774"),
                PASSWORD("Changeit"));
        
        recordAction.and(Do.say(VALUE( 
         "record your message after the tone.")));
        
        recordAction.choices(TERMINATOR("0,1,2,3,4,5,6,7,8,9,*,#"));
        
        return tropo.text();
    }
    
    
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/dumprecord",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
    public void uploadVoiceMsgOnObjectServer(
            @RequestPart(value = "filename", required = false) InputStream filename){
        
        System.out.println("*************************Inside DumpRecord*******************");
        final int DEFAULT_BUFFER_SIZE = 50000;
        OutputStream out;  
        try{
            File attachment = File.createTempFile("VoiceMail", "wav");
            out = new BufferedOutputStream(
                    new FileOutputStream(attachment), DEFAULT_BUFFER_SIZE);
          
            byte buffer[] = new byte[DEFAULT_BUFFER_SIZE];
            for (int length = 0; (length = filename.read(buffer)) > 0;) {
                    out.write(buffer, 0, length);
                       
            }
            }catch(Exception ex){
                ex.getStackTrace();
            }
        
    }
}
