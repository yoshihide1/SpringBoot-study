package com.yoshihide.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//追加
import org.springframework.data.domain.Pageable;
//ここまで
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yoshihide.springboot.repositories.MyDataMongoRepository;
//import com.yoshihide.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {

	@Autowired
	MyDataMongoRepository mongoRepository;

//	@Autowired
//	MyDataRepository myRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, /* 追加 */Pageable pageable) {
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataMongoのサンプル(/index)");
		Iterable<MyDataMongo> list = mongoRepository.findAll(pageable);
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@RequestParam("name") String name, @RequestParam("memo") String memo, ModelAndView mav) {
		MyDataMongo mydata = new MyDataMongo(name, memo);
		mongoRepository.save(mydata);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataのサンプル");
		mav.addObject("value", "");
		List<MyDataMongo> list = mongoRepository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("find") String param, ModelAndView mav) {
		mav.setViewName("find");
		if (param == "") {
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title", "Find Result");
			mav.addObject("msg", "「" + param + "」の検索結果");
			mav.addObject("value", param);
			List<MyDataMongo> list = mongoRepository.findByNameLike(param);
			mav.addObject("datalist", list);
		}
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "Delete Page");
		Optional<MyDataMongo> data = mongoRepository.findById((long) id);
		mav.addObject("formModel", data.get());
		return mav;
	}

}
