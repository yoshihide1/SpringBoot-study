package com.yoshihide.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yoshihide.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {

	@Autowired
	MyDataBean myDataBean;

	@Autowired
	MyDataRepository repository;

	@Autowired
	private MyDataService service;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, Pageable pageable) {
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		Page<MyData> list = repository.findAll(pageable);
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView indexById(@PathVariable long id, ModelAndView mav) {
		mav.setViewName("pickup");
		mav.addObject("title", "Pickup Page");
		String table = "<table>" + myDataBean.getTableTagById(id) + "</table>";
		mav.addObject("msg", "pickup data id = " + id);
		mav.addObject("data", table);
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataのサンプル(/index)");
		List<MyData> list = service.getAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataのサンプル(/find)");
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
			mav.addObject("title", "Find Result");
			mav.addObject("msg", "「" + param + "」の検索結果");
			mav.addObject("value", param);
			List<MyData> list = service.find(param);
			mav.addObject("datalist", list);
		}
		return mav;

	}

	@PostConstruct
	public void init() {
		// ダミーのデータ
		MyData d1 = new MyData();
		d1.setName("tetra");
		d1.setAge(20);
		d1.setMail("tetra@aqua.com");
		d1.setMemo("03911111111");
		repository.saveAndFlush(d1);

		MyData d2 = new MyData();
		d2.setName("hotal");
		d2.setAge(25);
		d2.setMail("hotal@aqua.com");
		d2.setMemo("22212235321");
		repository.saveAndFlush(d2);

		MyData d3 = new MyData();
		d3.setName("gecko");
		d3.setAge(33);
		d3.setMail("gecko@aqua.com");
		d3.setMemo("00033333333");
		repository.saveAndFlush(d3);

	}

}
