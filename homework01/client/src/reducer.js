import Generator from "./models/generator";
import Story from "./models/story";
import constants from "./constants";

export default function reducer (state, action) {
 switch (action.type) {
     case constants.actions.EXAMPLE:
         state.example = action.payload;
         return state;

     case constants.actions.BUY_GENERATOR:
         state.generators.forEach(generator => {
            if(generator.name === action.payload.name){
               let genModel = new Generator(generator);
               let generatorCost = genModel.getCost(); 
               if(generatorCost <= state.counter){ 
                  state.counter -= generatorCost.toFixed(2); 
                  generator.quantity++;
                  generator.unlockValue = genModel.getCost();
                }
                return state;
            }
        });
        return state;	

     case constants.actions.INCREMENT:
         state.counter += action.payload;
         return state;

     case constants.actions.CHECK_STORY:
         state.stories.forEach(story => {
           let storyModel = new Story(story);
           if (storyModel.isUnlockYet(state.counter)){
              storyModel.unlock();
              story.state = storyModel.state;
            }
            else{
              
              story.state = 'hidden';
            }
         });
          return state;
     default:
        return state;
 }
}

