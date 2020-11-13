package com.weimi.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.weimi.entity.Contant;
import com.weimi.entity.Result;
import com.weimi.utils.Base64Img;
import com.weimi.utils.StrUtil;

@RestController
@RequestMapping("/")
public class UploadController {

	@Value("${qiniu.live.accessKey}")
	private String accessKey;

	@Value("${qiniu.live.secretKey}")
	private String secretKey;

	@Value("${qiniu.oss.bucket}")
	private String bucket;

	@PostMapping("/batchupfile")
	@ResponseBody
	@CrossOrigin
	public String multipleFiles(@RequestParam(value = "file", required = false) MultipartFile[] files,
			@RequestParam("path") String path, HttpServletRequest request) {
		// List<MultipartFile> files = ((MultipartHttpServletRequest)
		// request).getFiles("file");
		if ((null == files || files.length == 0)) {
			Result result = new Result(Contant.ERR_BOOLEAN, Contant.EXCEPTION_CODE, Contant.ERR_MESSAGE, "文件上传不能为空");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			return json.toString();
		}
		String filePath = "D:\\down\\";

		if (path.equals("problems")) {
			filePath = "/opt/openresty/nginx/html/problems/"; // 问题反馈
		} else if ("registerV".equals(path)) {
			filePath = "/opt/openresty/nginx/html/registerV/"; // 注册视频
		} else if ("clubV".equals(path)) {
			Auth auth = Auth.create(accessKey, secretKey);
			/* 七牛上传不能自动获取区域，只能手动设置 */
			Zone z = Zone.zone1();
			Configuration c = new Configuration(z);
			UploadManager uploadManager = new UploadManager(c);
			Response res = null;
			try {
				res = uploadManager.put(files[0].getBytes(), files[0].getOriginalFilename(), auth.uploadToken(bucket));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != res) {
					res.close();
				}
			}

			Result result = new Result(Contant.BOOLEAN, Contant.CODE, Contant.MESSAGE, "文件上传成功");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			return json.toString();
		} else if ("heroV".equals(path)) {
			filePath = "/opt/openresty/nginx/html/heroV/"; // 英雄榜视频上传
		} else if ("phoneApply".equals(path)) {
			filePath = "/opt/openresty/nginx/html/phoneApply/"; // 更改手机号
		} else if ("postV".equals(path)) {
			filePath = "/opt/openresty/nginx/html/postV/"; // 发布说说
		} else if ("headV".equals(path)) {

			Auth auth = Auth.create(accessKey, secretKey);
			/* 七牛上传不能自动获取区域，只能手动设置 */
			Zone z = Zone.zone1();
			Configuration c = new Configuration(z);
			UploadManager uploadManager = new UploadManager(c);
			Response res = null;
			try {
				res = uploadManager.put(files[0].getBytes(), files[0].getOriginalFilename(), auth.uploadToken(bucket));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != res) {
					res.close();
				}
			}

			Result result = new Result(Contant.BOOLEAN, Contant.CODE, Contant.MESSAGE, "文件上传成功");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			return json.toString();

		} else if ("activityV".equals(path)) {
			filePath = "/opt/openresty/nginx/html/activityV/"; // 活动
		} else if ("bigV".equals(path)) {
			filePath = "/opt/openresty/nginx/html/bigV/";
		} else if("subject".equals(path)){
			Auth auth = Auth.create(accessKey, secretKey);
			/* 七牛上传不能自动获取区域，只能手动设置 */
			Zone z = Zone.zone1();
			Configuration c = new Configuration(z);
			UploadManager uploadManager = new UploadManager(c);
			Response res = null;
			try {
				res = uploadManager.put(files[0].getBytes(), files[0].getOriginalFilename(), auth.uploadToken(bucket));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != res) {
					res.close();
				}
			}
			Result result = new Result(Contant.BOOLEAN, Contant.CODE, Contant.MESSAGE, "文件上传成功");
			JSONObject json = (JSONObject) JSONObject.toJSON(result);
			return json.toString();
		}else {
			filePath = "/opt/openresty/nginx/html/video/";
		}

	if(null!=files&&files.length>0)

	{
		for (MultipartFile mf : files) {
			String filename = mf.getOriginalFilename();
			System.out.println(filename);
			if (mf.isEmpty()) {
				Result result = new Result(Contant.ERR_BOOLEAN, Contant.EXCEPTION_CODE, Contant.ERR_MESSAGE,
						"文件不能为空，上传失败");
				JSONObject json = (JSONObject) JSONObject.toJSON(result);
				return json.toString();
			}
			File dir = new File(filePath + filename);
			try {
				// 写入文件
				mf.transferTo(dir);
			} catch (IOException e) {
				Result result = new Result(Contant.ERR_BOOLEAN, Contant.EXCEPTION_CODE, Contant.ERR_MESSAGE, "文件上传失败");
				JSONObject json = (JSONObject) JSONObject.toJSON(result);
				return json.toString();
			}
		}
	}

	Result result = new Result(Contant.BOOLEAN, Contant.CODE, Contant.MESSAGE, "文件上传成功");
	JSONObject json = (JSONObject) JSONObject.toJSON(result);return json.toString();

}

}
