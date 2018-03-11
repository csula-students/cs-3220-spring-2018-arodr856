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
                state.counter -= generatorCost; 
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
              story.state = 'visible';
            }
         });
          return state;

     case constants.actions.CHECK_STORY:
          

        return state;
     default:
        return state;
 }
}

