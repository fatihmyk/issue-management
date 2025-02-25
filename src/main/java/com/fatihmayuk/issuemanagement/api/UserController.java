package com.fatihmayuk.issuemanagement.api;

import com.fatihmayuk.issuemanagement.dto.UserDto;
import com.fatihmayuk.issuemanagement.service.impl.UserServiceImpl;
import com.fatihmayuk.issuemanagement.util.ApiPaths;
import com.fatihmayuk.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author:FthMyk
 *
 *
 */

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL, description = "User APIs")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl ) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = UserDto.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable){
        TPage<UserDto> data = userServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "Get All By Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> data = userServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id",required = true) Long id) {
        UserDto userDto= userServiceImpl.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation",response = UserDto.class)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        return ResponseEntity.ok(userServiceImpl.save(userDto));
    }

    //@RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation",response = UserDto.class)
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id",required = true) Long id,@Valid @RequestBody UserDto userDto){

       return ResponseEntity.ok(userServiceImpl.update(id,userDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id",required = true) Long id){
        return ResponseEntity.ok(userServiceImpl.delete(id));
    }



}
