# Wallet API docs

so basically there are 2 endpoints. one for doing transactions and one for checking balance.

running on `http://localhost:8080`

---

## 1. Do a transaction

`POST /api/v1/wallet`

send this in the body:

```json
{
  "walletId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "operationType": "DEPOSIT",
  "amount": 500
}
```

operationType can be `DEPOSIT` or `WITHDRAW`, amount has to be more than 0

if it works you get back:

```json
{
  "walletId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "balance": 1500.00
}
```

---

## 2. Get balance

`GET /api/v1/wallets/{walletId}`

just put the wallet uuid in the url

```
GET /api/v1/wallets/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11
```

response:

```json
{
  "walletId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",
  "balance": 1500.00
}
```

---

## errors

if something goes wrong the response looks like this:

```json
{
  "timestamp": "2026-02-20T04:18:29.003Z",
  "status": 404,
  "error": "Not Found",
  "message": "Wallet not found: a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
}
```

- 400 - bad json or missing fields
- 404 - wallet doesnt exist
- 422 - not enough money to withdraw
- 500 - something broke on the server side

---

## curl examples

```bash
# check balance
curl http://localhost:8080/api/v1/wallets/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11

# deposit
curl -X POST http://localhost:8080/api/v1/wallet \
  -H "Content-Type: application/json" \
  -d '{"walletId":"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11","operationType":"DEPOSIT","amount":500}'

# withdraw
curl -X POST http://localhost:8080/api/v1/wallet \
  -H "Content-Type: application/json" \
  -d '{"walletId":"a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11","operationType":"WITHDRAW","amount":200}'
```

note: the seeded wallet id is `a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11` with 1000.00 balance, you can use it for testing