# alicetrustline


## Alice sends the credit - alice send the payment

API -> /api/sendpayment  to send payment to bob

<code>
curl -H "Content-Type: application/json" -X POST http://localhost:8080/alicetrustline/api/sendpayment -d "{\"credit\":10}"
</code>

### request body json
----------------

<code>
{
  "credit": 10
}
</code>
