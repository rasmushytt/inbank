import axios from "axios"
export default class AxiosServiceApi {
  constructor() {
    this.axiosServiceApi = axios.create({baseURL: "http://localhost:8083"})
  }
}
