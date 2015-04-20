package by.gwttest.client.client.gin;

import by.gwttest.client.client.application.ApplicationModule;
import by.gwttest.client.client.place.NameTokens;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new DefaultModule.Builder().tokenFormatter(
				RouteTokenFormatter.class).build());
		install(new ApplicationModule());

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.packet);
		bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.packet);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(
				NameTokens.packet);

	}
}