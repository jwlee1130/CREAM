package com.cream.controller;

public class ModelAndView {
		private String url;
		private boolean redirect;
		
		public ModelAndView(String url) {
			this.url = url;
		}
		
		public ModelAndView(String url, boolean redirect) {
			this(url);
			this.redirect = redirect;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public boolean isRedirect() {
			return redirect;
		}

		public void setRedirect(boolean redirect) {
			this.redirect = redirect;
		}
		
		
		
}
