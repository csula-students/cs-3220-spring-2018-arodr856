import Generator from "./models/generator";
import Story from "./models/story";

export default function reducer (state, action) {
 switch (action.type) {
   case 'EXAMPLE_MUTATION':
   state.example = action.payload;
   return state;
   case 'BUY_GENERATOR':
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

   case 'INCREMENT':
   state.counter += action.payload;
   return state;

   case 'CHECK_STORY':

    state.stories.forEach(story => {
     let storyModel = new Story(story);
     if (storyModel.isUnlockYet(state.counter)){
      story.state = 'visible';
     }
    });

   return state;
   default:
   return state;
 }
}

