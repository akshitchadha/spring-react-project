import { BASE_API_URL } from '../common/Constants';
import axios from 'axios';
import { authHeader } from './base.service';


const API_URL = BASE_API_URL + '/gateway/course';
const API_URL_CREATE_COURSE =API_URL+ '/createcourse';
const API_URL_DELETE_COURSE =API_URL+ '/deletecourse';
const API_URL_GET_COURSE =API_URL+ '/getcourses';
class CourseService {

    saveCourse(course) {
        return axios.post(API_URL_CREATE_COURSE, course, {headers: authHeader()});
    }

    deleteCourse(course) {
        return axios.delete(API_URL_DELETE_COURSE + '/' + course.id, {headers: authHeader()});
    }

    getAllCourses() {
        return axios.get(API_URL_GET_COURSE);
    }

}

export default new CourseService();
