package gov.usgs.wma.qw.codes.webservices;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import gov.usgs.wma.qw.BaseRestController;
import gov.usgs.wma.qw.LastUpdateDao;
import gov.usgs.wma.qw.codes.Code;
import gov.usgs.wma.qw.codes.CodeList;
import gov.usgs.wma.qw.codes.CodeType;
import gov.usgs.wma.qw.codes.dao.CodeDao;
import gov.usgs.wma.qw.springinit.ConfigOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Sample Media", description=ConfigOpenApi.LOOKUP_TAG_DESCRIPTION)
@RestController
@RequestMapping(value={"samplemedia"}, produces={BaseRestController.MEDIA_TYPE_APPLICATION_XML_UTF8_VALUE, BaseRestController.MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE})
public class SampleMediaRestController extends CodesRestController {

	private static final Logger LOG = LoggerFactory.getLogger(SampleMediaRestController.class);

	@Autowired
	public SampleMediaRestController(final LastUpdateDao lastUpdateDao, final CodeDao codeDao) {
		this.lastUpdateDao = lastUpdateDao;
		this.codeDao = codeDao;
	}

	@Operation(description="Return a filtered and paged list of valid Sample Media.")
	@GetMapping()
	public CodeList getSampleMedia(final @RequestParam(value="text", required=false) String text,
			final @RequestParam(value="pagenumber", required=false) String pageNumber,
			final @RequestParam(value="pagesize", required=false) String pageSize,
			WebRequest webRequest) {
		LOG.debug("samplemedia");
		return getList(CodeType.SAMPLEMEDIA, text, pageNumber, pageSize, null, webRequest);
	}

	@Operation(description="Validate and return the requested Sample Media.")
	@GetMapping("/validate")
	public Code getASampleMedia(final @RequestParam(value="value") String value, WebRequest webRequest, HttpServletResponse response) {
		LOG.debug("aSampleMedia");
		return getCode(CodeType.SAMPLEMEDIA, value, webRequest, response);
	}

}
