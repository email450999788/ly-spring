package com.liuy.spring.cloud.app.webfile.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class WebFileController {
	
	@SuppressWarnings("deprecation")
	@RequestMapping(path="/list", method=RequestMethod.GET)
    public String list(@RequestParam String path, Model m) {
		List<Map<String,String>> list = new ArrayList<>() ;
		File file = new File(path) ;
		if(file.exists()) {
			if(file.getParentFile() != null) {
				Map<String, String> parent = new HashMap<>() ;
				parent.put("displayValue", "..") ;
				parent.put("realValue", URLEncoder.encode(file.getParentFile().getAbsolutePath())) ;
				parent.put("file", "false") ;
				list.add(parent) ;
			}
			if(file.listFiles()!=null) {
				for(File listfile : file.listFiles()) {
					Map<String, String> row = new HashMap<>() ;
					row.put("displayValue", listfile.getName()) ;
					row.put("realValue", URLEncoder.encode(listfile.getAbsolutePath())) ;
					row.put("file", listfile.isFile() ? "true" : "false") ;
					list.add(row) ;
				}
			}
		}
		m.addAttribute("list", list) ;
        return "listfile";
    }
}
