package com.yoshihide.springboot;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yoshihide.springboot.repositories.MsgDataRepository;
import com.yoshihide.springboot.repositories.MyDataRepository;

@Controller
public class MsgDataController {

	@Autowired
	MsgDataRepository repository;

	@Autowired
	MyDataRepository myDataRepository;

	@PersistenceContext
	EntityManager entityManager;

	@RequestMapping(value = "/msg/{user_id}", method = RequestMethod.GET)
	public ModelAndView msg(ModelAndView mav, @PathVariable int user_id) {
		mav.setViewName("showMsgData");
		mav.addObject("title", "Sample");
		mav.addObject("msg", "MsgDataのサンプル");
		mav.addObject("user_id", (long) user_id);
		MsgData msgdata = new MsgData();
		mav.addObject("formModel", msgdata);
//		List<MsgData> list = (List<MsgData>) dao.getAll();
//		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/msg", method = RequestMethod.POST)
	public ModelAndView msgform(@Valid @ModelAttribute MsgData msgdata, Errors result, ModelAndView mav) {
		if (result.hasErrors()) {
			mav.setViewName("showMsgData");
			mav.addObject("title", "Sample [ERROR]");
			mav.addObject("msg", "値を再チェックしてください");
			mav.addObject("formModel", msgdata);
//			List<MsgData> list = (List<MsgData>) dao.getAll();
//			mav.addObject("datalist", list);
			return mav;
		} else {
			repository.saveAndFlush(msgdata);
			return new ModelAndView("redirect:/");
		}

	}

	@PostConstruct
	public void init() {
		System.out.println("ok");
//		dao = new MsgDataDaoImpl(entityManager);
	}
}