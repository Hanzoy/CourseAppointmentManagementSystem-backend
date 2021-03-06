package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.mapper.PictureMapper;
import com.hanzoy.yuekewei.mapper.VenueMapper;
import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.entity.Picture;
import com.hanzoy.yuekewei.service.CoachService;
import com.hanzoy.yuekewei.service.StaticResourceService;
import com.hanzoy.yuekewei.service.VenueService;
import com.hanzoy.yuekewei.utils.aliyunOSSUtils.AliyunOSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

@Api("微信客户端资源获取")
@RestController
public class ResourcesController {

    @Autowired
    StaticResourceService staticResourceService;

    @Autowired
    CoachService coachService;

    @Autowired
    VenueService venueService;

    @Resource
    VenueMapper venueMapper;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Resource
    PictureMapper pictureMapper;

    @ApiOperation("获取滑动条内容")
    @GetMapping("/getSwiper")
    public CommonResult<GetSwiperResult> getSwiper(){
        GetSwiperResult result = staticResourceService.getSwiper();
        return CommonResult.success(result);
    }

    @ApiOperation("管理员获取滑动条内容")
    @PostMapping("/manageGetSwiper")
    public CommonResult<ManageGetSwiperResult> manageGetSwiper(@RequestBody @Validated ManageGetSwiperParam param){
        ManageGetSwiperResult result = staticResourceService.manageGetSwiper(param);
        return CommonResult.success(result);
    }

    @ApiOperation("获取教练信息")
    @GetMapping("/getCoach")
    public CommonResult<GetCoachResult> getCoach(){
        GetCoachResult coach = coachService.getCoach();
        return CommonResult.success(coach);
    }

    @ApiOperation("删除教练信息")
    @PostMapping("/deleteCoach")
    public CommonResult<DeleteCoachResult> deleteCoach(@RequestBody @Validated DeleteCoachParam param){
        DeleteCoachResult result = coachService.deleteCoach(param);
        return CommonResult.success(result);
    }

    @ApiOperation("添加教练信息")
    @PostMapping("/addCoach")
    public CommonResult<AddCoachResult> addCoach(@RequestBody @Validated AddCoachParam param){
        AddCoachResult result = coachService.addCoach(param);
        return CommonResult.success(result);
    }

    @ApiOperation("获取场馆信息")
    @GetMapping("/getVenue")
    public CommonResult<GetVenueResult> getVenue(){
        GetVenueResult venue = venueService.getVenue();
        return CommonResult.success(venue);
    }

    @ApiOperation("查看所有图片")
    @PostMapping("/getAllPicture")
    public CommonResult<GetAllPictureResult> getAllPicture(@RequestBody @Validated GetAllPictureParam param){
        GetAllPictureResult allPicture = staticResourceService.getAllPicture(param);
        return CommonResult.success(allPicture);
    }

    @ApiOperation("上传并修改swiper图片")
    @RequestMapping(value = "/swiper/uploadFile", method = RequestMethod.POST)
    public String upLoadSwiper(@RequestParam("file") MultipartFile file, @RequestParam( value = "id", required = false)Integer id) {
        String fileName = file.getOriginalFilename();
        String uploadUrl = null;
        try {
            if (file != null) {
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    newFile.delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        pictureMapper.insertPicture(new Picture(null, uploadUrl, null));
        Integer pictureId = pictureMapper.getIdByUrl(uploadUrl);
        pictureMapper.changeSwiperPicture(pictureId, id);

        return uploadUrl;
    }
    @ApiOperation("上传并修改caoch图片")
    @RequestMapping(value = "/coach/uploadFile", method = RequestMethod.POST)
    public String upLoadCoach(@RequestParam("file") MultipartFile file, @RequestParam( value = "id", required = false)Integer id, @RequestParam(value = "number",required = false)Integer number) {
        String fileName = file.getOriginalFilename();
        String uploadUrl = null;
        try {
            if (file != null) {
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    newFile.delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        pictureMapper.insertPicture(new Picture(null, uploadUrl, null));
        Integer pictureId = pictureMapper.getIdByUrl(uploadUrl);
        if(number == 0){
            pictureMapper.changeCoachAvatarUrl(pictureId, id);
        }else{
            pictureMapper.changeCoachBackgroundUrl(pictureId, id);
        }

        return uploadUrl;
    }
    @ApiOperation("上传并修改venue图片")
    @RequestMapping(value = "/venue/uploadFile", method = RequestMethod.POST)
    public String upLoadVenue(@RequestParam("file") MultipartFile file, @RequestParam( value = "id", required = false)Integer id) {
        String fileName = file.getOriginalFilename();
        String uploadUrl = null;
        try {
            if (file != null) {
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    newFile.delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        pictureMapper.insertPicture(new Picture(null, uploadUrl, null));
        Integer pictureId = pictureMapper.getIdByUrl(uploadUrl);
//        pictureMapper.changeSwiperPicture(pictureId, id);
        venueMapper.changeVenuePicture(pictureId, id);
        return uploadUrl;
    }

    @ApiOperation("添加滑动条")
    @PostMapping("/addSwiper")
    public CommonResult<AddSwiperResult> addSwiper(@RequestBody @Validated AddSwiperParam param){
        AddSwiperResult result = staticResourceService.addSwiper(param);
        return CommonResult.success(result);
    }

    @ApiOperation("添加场馆")
    @PostMapping("/addVenue")
    public CommonResult<AddVenueResult> addVenue(@RequestBody @Validated AddVenueParam param){
        AddVenueResult result = venueService.addVenue(param);
        return CommonResult.success(result);
    }

    @ApiOperation("删除滑动条")
    @PostMapping("/deleteSwiper")
    public CommonResult<DeleteSwiperResult> deleteSwiper(@RequestBody @Validated DeleteSwiperParam param){
        DeleteSwiperResult result = staticResourceService.deleteSwiper(param);
        return CommonResult.success(result);
    }

    @ApiOperation("删除场馆")
    @PostMapping("/deleteVenue")
    public CommonResult<DeleteVenueResult> deleteSwiper(@RequestBody @Validated DeleteVenueParam param){
        DeleteVenueResult result = venueService.deleteVenue(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改滑动条")
    @PostMapping("/editSwiper")
    public CommonResult<EditSwiperResult> editSwiper(@RequestBody @Validated EditSwiperParam param){
        EditSwiperResult result = staticResourceService.editSwiper(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改场馆")
    @PostMapping("/editVenue")
    public CommonResult<EditVenueResult> editVenue(@RequestBody @Validated EditVenueParam param){
        EditVenueResult result = venueService.editVenue(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改教练信息")
    @PostMapping("/editCoach")
    public CommonResult<EditCoachResult> editCoach(@RequestBody @Validated EditCoachParam param){
        EditCoachResult result = coachService.editCoach(param);
        return CommonResult.success(result);
    }
}
