package abhibane;

import static com.voxeo.tropo.Key.NAME;
import static com.voxeo.tropo.Key.TIMEOUT;
import static com.voxeo.tropo.Key.TO;
import static com.voxeo.tropo.Key.EVENT;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.TropoSession;
import com.voxeo.tropo.actions.TransferAction;

@RestController
@RequestMapping("/abhibane/api/v1")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class Controller {
    
    @RequestMapping(value = "/addHeader", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE },
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String initiateCallSessions(@Valid @RequestBody TropoSession sessionBean, @RequestParam("headername") String headerName,
            @RequestParam("customerguid") String customerGuid, @RequestParam("headernumber") String headerNumber,
            @RequestParam("transfernumber") String transferNumber) {
    
        String headerContent = "<sip:" + headerNumber + "@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        //String diversion2 = "<sip:12345678@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        //String diversion3 = "<sip:23456789@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        //String diversion4 = "<sip:3456789@cisco.com>;x-cisco-tenant=" + customerGuid + ";reason=no-answer;privacy=off;screen=yes;";
        
        Tropo tropo = new Tropo();
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
}
