package com.example.onlineexamination;

public class cQuestions {

    private int id;

    private String question;

    private String option_one;

    private String option_two;

    private String option_three;

    private String option_four;

    private String correct_option;

    public cQuestions(int id, String question, String option_one, String option_two, String option_three, String option_four, String correct_option) {
        this.id = id;
        this.question = question;
        this.option_one = option_one;
        this.option_two = option_two;
        this.option_three = option_three;
        this.option_four = option_four;
        this.correct_option = correct_option;
    }

  /*  public cQuestions(String ids, String string, String string1, String string2, String string3, String string4, String string5) {
    }*/

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption_one() {
        return option_one;
    }

    public String getOption_two() {
        return option_two;
    }

    public String getOption_three() {
        return option_three;
    }

    public String getOption_four() {
        return option_four;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public Object getSelectedRadioButtonId() {
        return null;
    }

    public void setSelectedRadioButtonId(int radioButtonID) {
    }
}
/*extends AsyncTask<Void, Void, String> {

    String data = "";
    @Override
    protected String doInBackground(Void... voids) {

        try {
            URL url = new URL(URL_C);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
         //   String choos_textView = null;
            params.put("c_ans", choos_textView);
          //  params.put("password", password);

            //returing the response

            return requestHandler.sendPostRequest(Constants.URL_C_ANSWER, params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);



    }
}
*/