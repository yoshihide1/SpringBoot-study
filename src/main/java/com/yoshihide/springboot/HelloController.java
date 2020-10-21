package com.yoshihide.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;

import com.yoshihide.springboot.repositories.MyDataRepository;

//class DataObject {
//	private int id;
//	private String name;
//	private String value;
//
//	public DataObject(int id, String name, String value) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.value = value;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getValue() {
//		return value;
//	}
//
//	public void setValue(String value) {
//		this.value = value;
//	}
//}

@Controller
public class HelloController {

	@Autowired
	MyDataRepository repository;

	@Autowired
	private MyDataService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataのサンプル");
		List<MyData> list = service.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataのサンプルです");
		mav.addObject("value", "");
		List<MyData> list = service.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if (param == "") {
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title", "Find result");
			mav.addObject("msg", "|「${param}」の検索結果|");
			mav.addObject("value", param);
			List<MyData> list = service.find(param);
			mav.addObject("datalist", list);
		}
		return mav;
	}

//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	@Transactional(readOnly = false)
//	public ModelAndView form(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
//		repository.saveAndFlush(mydata);
//		return new ModelAndView("redirect:/");
//	}

	@PostConstruct
	public void init() {
		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("tuyano@flower.com");
		d1.setMemo("this is my data!");
		repository.saveAndFlush(d1);

		MyData d2 = new MyData();
		d2.setName("yoshi");
		d2.setAge(23);
		d2.setMail("yoshihide@flower.com");
		d2.setMemo("this is yoshi data!");
		repository.saveAndFlush(d2);

		MyData d3 = new MyData();
		d3.setName("kenji");
		d3.setAge(13);
		d3.setMail("kenji@flower.com");
		d3.setMemo("this is kenji data!");
		repository.saveAndFlush(d3);
	}

//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//	public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
//		mav.setViewName("edit");
//		mav.addObject("title", "edit mydata.");
//		Optional<MyData> data = repository.findById((long) id);
//		mav.addObject("formModel", data.get());
//		return mav;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	@Transactional(readOnly = false)
//	public ModelAndView update(@ModelAttribute MyData mydata, ModelAndView mav) {
//		repository.saveAndFlush(mydata);
//		return new ModelAndView("redirect:/");
//	}
//
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
//		mav.setViewName("delete");
//		mav.addObject("title", "delete mydata");
//		Optional<MyData> data = repository.findById((long) id);
//		mav.addObject("formModel", data.get());
//		return mav;
//	}
//
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	@Transactional(readOnly = false)
//	public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
//		repository.deleteById(id);
//		return new ModelAndView("redirect:/");
//	}
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView index(ModelAndView mav) {
//		mav.setViewName("index");
//		mav.addObject("msg", "current data.");
//		DataObject obj = new DataObject(123, "hanako", "hanako@flower.com");
//		mav.addObject("object", obj);
//		return mav;
//	}

//	@RequestMapping("/{id}")
//	public ModelAndView index(@PathVariable int id, ModelAndView mav) {
//		mav.setViewName("index");
//		mav.addObject("id", id);
//		mav.addObject("check", id % 2 == 0);
//		mav.addObject("trueVal", "Even number!");
//		mav.addObject("falseVal", "Odd number...");
//		return mav;
//	}
//
//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	public ModelAndView send(@RequestParam(value = "check1", required = false) boolean check1,
//			@RequestParam(value = "radio1", required = false) String radio1,
//			@RequestParam(value = "select1", required = false) String select1,
//			@RequestParam(value = "select2", required = false) String[] select2, ModelAndView mav) {
//
//		String res = "";
//		try {
//			res = "check:" + check1 + " radio:" + radio1 + " select:" + select1 + "\nselect2:";
//		} catch (NullPointerException e) {
//		}
//		try {
//			res += select2[0];
//			for (int i = 1; i < select2.length; i++) {
//				res += ", " + select2[i];
//			}
//		} finally {
//			res += "null";
//		}
//		mav.addObject("msg", res);
//		mav.setViewName("index");
//		return mav;
//	}
//
//	@RequestMapping("/home")
//	public String sample(ModelAndView mav) {
//		mav.setViewName("home");
//		return "forward:/";
//	}

}
