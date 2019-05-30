/**
 * 
 */
package com.ebtrust.iwealth.oss.controller;

import com.vanseed.rhea.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * @author leon
 *
 */
@Controller
public class BaseController {
	@Autowired
	protected ISessionService sessionService;
}
