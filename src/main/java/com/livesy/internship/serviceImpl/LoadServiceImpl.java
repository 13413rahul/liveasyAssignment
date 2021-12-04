package com.livesy.internship.serviceImpl;

import com.livesy.internship.entity.Load;
import com.livesy.internship.entity.Location;
import com.livesy.internship.entity.Product;
import com.livesy.internship.entity.Truck;
import com.livesy.internship.repository.LoadRepository;
import com.livesy.internship.repository.LocationRepository;
import com.livesy.internship.repository.ProductRepository;
import com.livesy.internship.repository.TruckRepository;
import com.livesy.internship.request.InsertLoadRequest;
import com.livesy.internship.request.UpdateLoadRequest;
import com.livesy.internship.response.LoadIdResponse;
import com.livesy.internship.response.LoadResponse;
import com.livesy.internship.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class LoadServiceImpl implements LoadService {
    @Autowired
    LoadRepository loadRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TruckRepository truckRepository;

    @Override
    public ResponseEntity<String> registerPayload(InsertLoadRequest insertLoadRequest) {

        // no of truck and weight is grater than 0 or not
        if(insertLoadRequest.getNoOfTrucks() <= 0 || insertLoadRequest.getWeight() <= 0) {
            return new ResponseEntity<>("numeric value not greater than 0", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // it used for format the date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
		String dateString = localDate.format(dtf);


        try {
            String loadingPoint = insertLoadRequest.getLoadingPoint();
            // loadingPoint is exists in our db or not if not than insert it or exists in db than simply fetch from db
            if(!loadingPoint.isEmpty() && !locationRepository.existsByLocationName(loadingPoint)) {
                locationRepository.insertIntoLocationTable(loadingPoint);
            }
            Location loading = locationRepository.findByLocationName(loadingPoint);

            // unloadingPoint is exists in our db or not if not than insert it or exists in db than simply fetch from db
            String unloadingPoint = insertLoadRequest.getUnloadingPoint();
            if(!unloadingPoint.isEmpty() && !locationRepository.existsByLocationName(unloadingPoint)) {
                locationRepository.insertIntoLocationTable(unloadingPoint);
            }
            Location unloading = locationRepository.findByLocationName(unloadingPoint);

            // productType is exists in our db or not if not than insert it or exists in db than simply fetch from db
            String productType = insertLoadRequest.getProductType();
            if(!productType.isEmpty() && !productRepository.existsByProductType(productType)) {
                productRepository.insertIntoProductTable(productType);
            }
            Product product = productRepository.findByProductType(productType);

            // truckType is exists in our db or not if not than insert it or exists in db than simply fetch from db
            String truckType = insertLoadRequest.getTruckType();
            if(!truckType.isEmpty() && !truckRepository.existsByTruckType(truckType)) {
                truckRepository.insertIntoTruckTable(truckType);
            }
            Truck truck = truckRepository.findByTruckType(truckType);

            // generate UUID using UUID class
            UUID uuid=UUID.randomUUID();
            String shipperId = "shipper:" + uuid;


            loadRepository.insertIntoLoadTable(loading.getLocationId(), unloading.getLocationId(), truck.getTruckId(), product.getProductId(),shipperId,
                    insertLoadRequest.getComment(),insertLoadRequest.getNoOfTrucks(),dateString,insertLoadRequest.getWeight());

        }catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.EXPECTATION_FAILED);
        }


        return new ResponseEntity<>("loads details added successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LoadResponse>> getLoadByShipperId(String shipperId) {
        List<LoadResponse> allLoadsWithShipperId = new ArrayList<>();

        LoadResponse loadResponse = null;

            List<Load> loadsWithShipperId = loadRepository.findByShipperId(shipperId);

            // any load exists with given shipperId or not
            if(loadsWithShipperId.isEmpty()) {
                throw new EntityNotFoundException("Load not found for shipperId: " + shipperId);
            }


            for (Load load : loadsWithShipperId) {
                loadResponse = new LoadResponse();

                // setter for loadId
                loadResponse.setLoadId(load.getLoadId());

                // setter for loadingPoint
                Long loadingPointId = load.getLoadingPointId();
                Location loading = locationRepository.findByLocationId(loadingPointId);
                loadResponse.setLoadingPoint(loading.getLocationName());

                // setter for unloadingPoint
                Long unloadingPointId = load.getUnloadingPointId();
                Location unloading = locationRepository.findByLocationId(unloadingPointId);
                loadResponse.setUnloadingPoint(unloading.getLocationName());

                // setter for productType
                Long productTypeId = load.getProductTypeId();
                Product product = productRepository.findByProductId(productTypeId);
                loadResponse.setProductType(product.getProductType());

                // setter for truckType
                Long truckTypeId = load.getTruckTypeId();
                Truck truck = truckRepository.findByTruckId(truckTypeId);
                loadResponse.setTruckType(truck.getTruckType());

                // setter for weight
                loadResponse.setWeight(load.getWeight());

                // setter for noOfTruck
                loadResponse.setNoOfTrucks(load.getNoOfTrucks());

                // setter for date
                loadResponse.setDate(load.getDate());

                // setter for Comment
                loadResponse.setComment(load.getComment());

                // add every load into list
                allLoadsWithShipperId.add(loadResponse);
            }

        return new ResponseEntity<>(allLoadsWithShipperId,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoadIdResponse> getLoadWithLoadId(Long loadId) {
        LoadIdResponse loadIdResponse = new LoadIdResponse();

        Load loadWithLoadId = loadRepository.findByLoadId(loadId);

        // load with given loadId exists in db or not
        if(loadWithLoadId == null) {
            throw new EntityNotFoundException("Load not found for loadId: " + loadId);
        }

        // setter for loadingPoint
        Long loadingPointId = loadWithLoadId.getLoadingPointId();
        Location loading = locationRepository.findByLocationId(loadingPointId);
        loadIdResponse.setLoadingPoint(loading.getLocationName());

        // setter for unloadingPoint
        Long unloadingPointId = loadWithLoadId.getUnloadingPointId();
        Location unloading = locationRepository.findByLocationId(unloadingPointId);
        loadIdResponse.setUnloadingPoint(unloading.getLocationName());

        // setter for productType
        Long productTypeId = loadWithLoadId.getProductTypeId();
        Product product = productRepository.findByProductId(productTypeId);
        loadIdResponse.setProductType(product.getProductType());

        // setter for truckType
        Long truckTypeId = loadWithLoadId.getTruckTypeId();
        Truck truck = truckRepository.findByTruckId(truckTypeId);
        loadIdResponse.setTruckType(truck.getTruckType());

        // setter for weight
        loadIdResponse.setWeight(loadWithLoadId.getWeight());

        // setter for noOfTruck
        loadIdResponse.setShipperId(loadWithLoadId.getShipperId());

        // setter for date
        loadIdResponse.setNoOfTrucks(loadWithLoadId.getNoOfTrucks());

        // setter for Comment
        loadIdResponse.setDate(loadWithLoadId.getDate());

        // add every load into list
        loadIdResponse.setComment(loadWithLoadId.getComment());


        return new ResponseEntity<>(loadIdResponse,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> updateLoadWithLoadId(Long loadId, UpdateLoadRequest updateLoadRequest) {

        // loadingPoint is exists in our db or not if not than insert it or exists in db than simply fetch from db
        String loadingPoint = updateLoadRequest.getLoadingPoint();
        if (!locationRepository.existsByLocationName(loadingPoint)) {
            locationRepository.insertIntoLocationTable(loadingPoint);
        }
        Location loading = locationRepository.findByLocationName(loadingPoint);

        // unloadingPoint is exists in our db or not if not than insert it or exists in db than simply fetch from db
        String unloadingPoint = updateLoadRequest.getUnloadingPoint();
        if (!locationRepository.existsByLocationName(unloadingPoint)) {
            locationRepository.insertIntoLocationTable(unloadingPoint);
        }
        Location unloading = locationRepository.findByLocationName(unloadingPoint);

        // productType is exists in our db or not if not than insert it or exists in db than simply fetch from db
        String productType = updateLoadRequest.getProductType();
        if (!productRepository.existsByProductType(productType)) {
            productRepository.insertIntoProductTable(productType);
        }
        Product product = productRepository.findByProductType(productType);

        // truckType is exists in our db or not if not than insert it or exists in db than simply fetch from db
        String truckType = updateLoadRequest.getTruckType();
        if (!truckRepository.existsByTruckType(truckType)) {
            truckRepository.insertIntoTruckTable(truckType);
        }
        Truck truck = truckRepository.findByTruckType(truckType);

        loadRepository.updateWithLoadId(loading.getLocationId(), unloading.getLocationId(), product.getProductId(), truck.getTruckId(),
                updateLoadRequest.getNoOfTrucks(), updateLoadRequest.getWeight(), updateLoadRequest.getComment(), updateLoadRequest.getDate(), loadId);

        return new ResponseEntity<>("load details updated successfully", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> deleteLoadWithLoadId(Long loadId) {
            loadRepository.deleteByLoadId(loadId);

        return new ResponseEntity<>("load details deleted successfully", HttpStatus.OK);
    }
}
