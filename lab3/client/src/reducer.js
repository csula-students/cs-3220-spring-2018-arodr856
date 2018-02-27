export default function reducer (state, action) {
    switch (action.type) {
        case 'EXAMPLE_MUTATION':
            state.example = action.payload;
            return state;
        case 'BUY_GENERATOR':

            if(action.generator_id == '0'){
                if(state.gen_0_quantity == null){
                    state.gen_0_quantity = 1;
                }
                else{
                    state.gen_0_quantity = state.gen_0_quantity + action.payload;
                }
            }

            if(action.generator_id == '1'){
                if(state.gen_1_quantity == null){
                    state.gen_1_quantity = 1;
                }
                else{
                    state.gen_1_quantity = state.gen_1_quantity + action.payload;
                }
            }

            if(action.generator_id == '2'){
                if(state.gen_2_quantity == null){
                    state.gen_2_quantity = 1;
                }
                else{
                    state.gen_2_quantity = state.gen_2_quantity + action.payload;
                }
            }

            return state;
        default:
            return state;
    }
}

