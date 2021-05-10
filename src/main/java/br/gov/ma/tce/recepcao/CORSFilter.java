package br.gov.ma.tce.recepcao;

//@Configuration
public class CORSFilter {
	
	/*
	 * 
	 * @Override public void doFilter(ServletRequest req, ServletResponse res,
	 * FilterChain chain) throws IOException, ServletException {
	 * 
	 * System.out.println("passando no filtro do cors");
	 * 
	 * HttpServletResponse response = (HttpServletResponse) res;
	 * 
	 * HttpServletRequest request = (HttpServletRequest) req;
	 * 
	 * response.setHeader("Access-Control-Allow-Origin", "*");
	 * response.setHeader("Access-Control-Allow-Methods",
	 * "POST, GET, PUT, OPTIONS, DELETE, PATCH");
	 * response.setHeader("Access-Control-Max-Age", "3600");
	 * response.setHeader("Access-Control-Allow-Headers",
	 * "Origin, X-Requested-With, Content-Type, Accept");
	 * response.setHeader("Access-Control-Expose-Headers", "Location");
	 * 
	 * 
	 * if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	 * 
	 * response.setStatus(HttpServletResponse.SC_OK); }else {
	 * 
	 * 
	 * chain.doFilter(req, res); }
	 * 
	 * 
	 * }
	 * 
	 * @Override public void init(FilterConfig filterConfig) {}
	 * 
	 * @Override public void destroy() {}
	 */

}
