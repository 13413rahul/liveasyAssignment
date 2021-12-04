package com.livesy.internship.controller;

import com.livesy.internship.request.InsertLoadRequest;
import com.livesy.internship.request.UpdateLoadRequest;
import com.livesy.internship.response.LoadIdResponse;
import com.livesy.internship.response.LoadResponse;
import com.livesy.internship.service.LoadService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/loads")
public class LoadController {

    @Autowired
    LoadService loadService;

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public ResponseEntity<String> registerAllPayload(@RequestBody InsertLoadRequest insertLoadRequest) {
        return loadService.registerPayload(insertLoadRequest);
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ResponseEntity<List<LoadResponse>> getLoadsByShipperId(@NonNull @PathParam("shipperId") String shipperId) {
        return loadService.getLoadByShipperId(shipperId);
    }

    @RequestMapping(value = "/load/{loadId}", method = RequestMethod.GET)
    public ResponseEntity<LoadIdResponse> getLoadByLoadId(@PathVariable("loadId") Long loadId) {
        return loadService.getLoadWithLoadId(loadId);
    }

    @RequestMapping(value = "/load/{loadId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateLoadByLoadId(@PathVariable("loadId") Long loadId, @RequestBody UpdateLoadRequest updateLoadRequest) {
        return loadService.updateLoadWithLoadId(loadId, updateLoadRequest);
    }

    @RequestMapping(value = "/load/{loadId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLoadByLoadId(@PathVariable("loadId") Long loadId) {
        return loadService.deleteLoadWithLoadId(loadId);
    }

}
