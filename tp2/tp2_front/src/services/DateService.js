import * as moment from 'moment'

export default {

    getFullDate(timestamp) {
        moment.locale('fr');
        return moment.unix(timestamp).format('LL');
    }

}