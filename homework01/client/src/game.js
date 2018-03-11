import Generator from "./models/generator";
import constants from "./constants";
// default interval as 1 second
const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */
export function loop (store) {
	
	let resourcesToAdd = 0;
	store.state.generators.forEach( generator =>{
		let genModel = new Generator(generator);
		resourcesToAdd += genModel.generate();
	});
	store.dispatch({
		type: constants.actions.INCREMENT,
		payload: resourcesToAdd
	});

	store.dispatch({type: constants.actions.CHECK_STORY});

	// recursively calls loop method every second
	setTimeout(loop.bind(this, store), interval);
}
